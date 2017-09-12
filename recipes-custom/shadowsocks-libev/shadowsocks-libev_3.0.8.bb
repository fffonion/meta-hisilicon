DESCRIPTION = "libev port of shadowsocks"
SECTION = "console/network"
PRIORITY = "optional"
LICENSE = "GPL"
DEPENDS = "mbedtls udns libev libsodium"
PR = "r0"

SRC_URI = "gitsm://github.com/shadowsocks/shadowsocks-libev.git;protocol=https;tag=v${PV}"

S = "${WORKDIR}/git"

inherit autotools

LDFLAGS += "-L${STAGING_LIBDIR}"
CFLAGS += "-L${STAGING_LIBDIR} -I${STAGING_INCDIR}"

do_configure() {
	cd ${S}
        ./autogen.sh
	oe_runconf --disable-documentation
}

do_compile() {
        cd ${S}/libbloom
        make clean
        oe_runmake -C ${S} CFLAGS="${CFLAGS}" LDFLAGS="${LDFLAGS}"
        cd ..
        oe_runmake -C ${S} CFLAGS="${CFLAGS}" LDFLAGS="${LDFLAGS}"
}

do_install() {
        mkdir -p ${D}/${bindir}
        install -m 0755 ${S}/src/ss-local ${D}/${bindir}
        install -m 0755 ${S}/src/ss-manager ${D}/${bindir}
        install -m 0755 ${S}/src/ss-server ${D}/${bindir}
        install -m 0755 ${S}/src/ss-redir ${D}/${bindir}
        install -m 0755 ${S}/src/ss-tunnel ${D}/${bindir}
        install -m 0755 ${S}/src/ss-nat ${D}/${bindir}
}
SRC_URI[md5sum] = "4423129a2a838cf8884075551ce14a36"
SRC_URI[sha256sum] = "1c30646bc716c18c5e425011712632983af7c942e6157aed55a3452a30cdc07e"
