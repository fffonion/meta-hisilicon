SUMMARY = "HTTP and reverse proxy server"

DESCRIPTION = "OpenResty is a dynamic web platform based on NGINX and LuaJIT."

HOMEPAGE = "https://openresty.org/"
LICENSE = "BSD-2-Clause"

SECTION = "net"

DEPENDS = "libpcre gzip openssl readline luajit"

PR = "r1"

SRC_URI = " \
        https://openresty.org/download/openresty-${PV}.tar.gz \
	file://nginx-cross.patch \
	file://resty-dyn.patch \
	file://nginx.conf \
	file://nginx.init \
	file://nginx-volatile.conf \
	file://nginx.service \
        git://github.com/arut/nginx-dav-ext-module.git;protocol=https;rev=master;destsuffix=modules/nginx-dav-ext-module \
        git://github.com/vozlt/nginx-module-stream-sts.git;protocol=https;rev=master;destsuffix=modules/nginx-module-stream-sts \
        git://github.com/vozlt/nginx-module-sts.git;protocol=https;rev=master;destsuffix=modules/nginx-module-sts \
        git://github.com/vozlt/nginx-module-vts.git;protocol=https;rev=master;destsuffix=modules/nginx-module-vts \
        git://github.com/arut/nginx-rtmp-module.git;protocol=https;rev=master;destsuffix=modules/nginx-rtmp-module \
        git://github.com/openresty/stream-lua-nginx-module.git;protocol=https;rev=a3a050bfacfb8d097ee276380c4e606031f2aaf2;destsuffix=modules/stream-lua-nginx-module \
"

inherit update-rc.d useradd

LDFLAGS += " -L${STAGING_LIBDIR}"
CFLAGS += " -L${STAGING_LIBDIR} -I${STAGING_INCDIR}/luajit-2.1  -fPIE -pie"
CXXFLAGS += " -fPIE -pie"
INSANE_SKIP_${PN} = "useless-rpaths"

NGINX_WWWDIR ?= "${localstatedir}/www/localhost"
NGINX_USER   ?= "www"

EXTRA_OECONF = ""
DISABLE_STATIC = ""

do_patch () {
        cd ${S}
        patch -p1 < ../resty-dyn.patch
        cd bundle/nginx-*
        patch -p1 < ../../../nginx-cross.patch
        cd ..
        # skip -R
        # sed -i "s/-R\/usr\/lib//g" */config
        # sed -i 's/-R$LUAJIT_LIB//g' ngx_lua-*/config
        # sed -i 's/-R$LUA_LIB//g' ngx_lua-*/config
}

do_configure () {
	if [ "${SITEINFO_BITS}" = "64" ]; then
		PTRSIZE=8
	else
		PTRSIZE=4
	fi

	echo CFLAGS: $CFLAGS
	echo LDFLAGS: $LDFLAGS
        echo CC: $CC
        
        

        ./configure \
            --crossbuild=Linux:${TUNE_ARCH} \
            --with-endian=${@base_conditional('SITEINFO_ENDIANNESS', 'le', 'little', 'big', d)} \
            --with-int=4 \
            --with-long=${PTRSIZE} \
            --with-long-long=8 \
            --with-ptr-size=${PTRSIZE} \
            --with-sig-atomic-t=${PTRSIZE} \
            --with-size-t=${PTRSIZE} \
            --with-off-t=${PTRSIZE} \
            --with-time-t=${PTRSIZE} \
            --with-sys-nerr=132 \
            --with-cc="${CC} ${CFLAGS}" \
            --conf-path=${sysconfdir}/nginx/nginx.conf \
            --http-log-path=${localstatedir}/log/nginx/access.log \
            --error-log-path=${localstatedir}/log/nginx/error.log \
            --pid-path=/run/nginx/nginx.pid \
            --prefix=/usr/local/openresty \
            --with-luajit=/usr \
            --lock-path=${localstatedir}/lock/nginx.lock \
            --http-client-body-temp-path=${localstatedir}/lib/nginx/body \
            --http-fastcgi-temp-path=${localstatedir}/lib/nginx/fastcgi \
            --http-proxy-temp-path=${localstatedir}/lib/nginx/proxy \
            --http-scgi-temp-path=${localstatedir}/lib/nginx/scgi \
            --http-uwsgi-temp-path=${localstatedir}/lib/nginx/uwsgi \
            --with-threads \
            --with-pcre-jit \
            --with-ipv6 \
            --with-file-aio  \
            --with-http_ssl_module \
            --with-http_realip_module  \
            --with-http_gzip_static_module \
            --with-http_v2_module \
            --with-stream \
            --without-http_ssi_module --without-http_userid_module --without-http_uwsgi_module --without-http_scgi_module --without-http_empty_gif_module --without-http_browser_module --without-http_memcached_module \
            --with-http_ssl_module --with-http_realip_module --with-http_addition_module --with-http_gzip_static_module --with-http_v2_module --with-http_sub_module --with-stream_ssl_module --with-http_stub_status_module --with-http_dav_module --with-http_slice_module \
            --add-dynamic-module=../../../modules/nginx-dav-ext-module \
            --add-dynamic-module=../../../modules/nginx-rtmp-module \
            --add-dynamic-module=../../../modules/nginx-module-vts \
            --add-dynamic-module=../../../modules/stream-lua-nginx-module \
	${EXTRA_OECONF}
            # --add-dynamic-module=../../../modules/nginx-module-sts \
            # --add-dynamic-module=../../../modules/nginx-module-stream-sts \
}

do_install () {
	oe_runmake 'DESTDIR=${D}' install

	rm -fr ${D}${localstatedir}/run ${D}/run
	if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
		install -d ${D}${sysconfdir}/tmpfiles.d
		echo "d /run/${BPN} - - - -" \
		     > ${D}${sysconfdir}/tmpfiles.d/${BPN}.conf
	fi
	install -d ${D}${sysconfdir}/${BPN}
	ln -snf ${localstatedir}/run/${BPN} ${D}${sysconfdir}/${BPN}/run
        # install -d ${D}${NGINX_WWWDIR}
        # mv ${D}/usr/html ${D}${NGINX_WWWDIR}/
        chown ${NGINX_USER}:www-data -R ${D}/usr/local/openresty/nginx/html

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/nginx.init ${D}${sysconfdir}/init.d/nginx
	sed -i 's,/usr/sbin/,${sbindir}/,g' ${D}${sysconfdir}/init.d/nginx
	sed -i 's,/etc/,${sysconfdir}/,g'  ${D}${sysconfdir}/init.d/nginx

	install -d ${D}${sysconfdir}/nginx
	install -m 0644 ${WORKDIR}/nginx.conf ${D}${sysconfdir}/nginx/nginx.conf
	sed -i 's,/var/,${localstatedir}/,g' ${D}${sysconfdir}/nginx/nginx.conf
	install -d ${D}${sysconfdir}/nginx/sites-enabled

	install -d ${D}${sysconfdir}/default/volatiles
	install -m 0644 ${WORKDIR}/nginx-volatile.conf ${D}${sysconfdir}/default/volatiles/99_nginx
	sed -i 's,/var/,${localstatedir}/,g' ${D}${sysconfdir}/default/volatiles/99_nginx

        if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)};then
            install -d ${D}${systemd_unitdir}/system
            install -m 0644 ${WORKDIR}/nginx.service ${D}${systemd_unitdir}/system/
            sed -i -e 's,@SYSCONFDIR@,${sysconfdir},g' \
                    -e 's,@LOCALSTATEDIR@,${localstatedir},g' \
                    -e 's,@BASEBINDIR@,${base_bindir},g' \
                    ${D}${systemd_unitdir}/system/nginx.service
        fi
}

pkg_postinst_${PN} () {
	if [ -z "$D" ]; then
		if type systemd-tmpfiles >/dev/null; then
			systemd-tmpfiles --create
		elif [ -e ${sysconfdir}/init.d/populate-volatile.sh ]; then
			${sysconfdir}/init.d/populate-volatile.sh update
		fi
	fi
}

FILES_${PN} += "${localstatedir}/ \
                /usr/local/openresty \
                ${systemd_unitdir}/system/nginx.service \
                "

CONFFILES_${PN} = "${sysconfdir}/nginx/nginx.conf \
		${sysconfdir}/nginx/fastcgi.conf\
		${sysconfdir}/nginx/fastcgi_params \
		${sysconfdir}/nginx/koi-utf \
		${sysconfdir}/nginx/koi-win \
		${sysconfdir}/nginx/mime.types \
		${sysconfdir}/nginx/scgi_params \
		${sysconfdir}/nginx/uwsgi_params \
		${sysconfdir}/nginx/win-utf \
"

INITSCRIPT_NAME = "nginx"
INITSCRIPT_PARAMS = "defaults 92 20"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = " \
    --system --no-create-home \
    --groups www-data \
    --user-group ${NGINX_USER}"
