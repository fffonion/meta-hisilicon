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
SRC_URI[md5sum] = "798a48457c87cb3167ba82d8a7348adb"
SRC_URI[sha256sum] = "4abd07b10476039190a6a60e822370d16750cb046f0bcb4877501e7d042c39e5"
