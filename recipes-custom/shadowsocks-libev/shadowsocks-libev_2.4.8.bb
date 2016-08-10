DESCRIPTION = "libev port of shadowsocks"
SECTION = "console/network"
PRIORITY = "optional"
LICENSE = "GPL"
DEPENDS = "openssl"
PR = "r0"

SRC_URI = "https://github.com/shadowsocks/shadowsocks-libev/archive/v${PV}.tar.gz"

S = "${WORKDIR}/shadowsocks-libev-${PV}"

inherit autotools

LDFLAGS += "-L${STAGING_LIBDIR}"
CFLAGS += "-L${STAGING_LIBDIR} -I${STAGING_INCDIR}"

do_configure() {
	cd ${S}
	autoreconf --force --install
	oe_runconf --disable-documentation
}

do_compile() {
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

SRC_URI[md5sum] = "8b300e5732a594245fa9c08fd7fbf738"
SRC_URI[sha256sum] = "af3fc3be50fb24dfd1aed8ce34d2d13448735f6181269f54f4860438a1838472"
