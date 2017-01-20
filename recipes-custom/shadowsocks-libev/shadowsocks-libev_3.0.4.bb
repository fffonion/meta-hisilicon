DESCRIPTION = "libev port of shadowsocks"
SECTION = "console/network"
PRIORITY = "optional"
LICENSE = "GPL"
DEPENDS = "libev libudns libsodium libmbedtls"
PR = "r0"

SRC_URI = "https://github.com/shadowsocks/shadowsocks-libev/releases/download/v${PV}/shadowsocks-libev-${PV}.tar.gz"

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
SRC_URI[md5sum] = "67c9e5b4223f6dfd8ae1084af2c99b78"
SRC_URI[sha256sum] = "f2dda87a3c25574b560521455a28463b43be0c092ed74551f9ef6aeb76e5ce75"
