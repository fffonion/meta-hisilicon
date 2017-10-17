DESCRIPTION = "aria2 is a lightweight multi-protocol & multi-source command-line download utility. \
	It supports HTTP/HTTPS, FTP, SFTP, BitTorrent and Metalink. \
	aria2 can be manipulated via built-in JSON-RPC and XML-RPC interfaces."
SECTION = "console/network"
PRIORITY = "optional"
LICENSE = "GPL"
DEPENDS = "c-ares expat zlib gnutls nettle"
PR = "r0"

SRC_URI = "https://github.com/aria2/aria2/releases/download/release-${PV}/aria2-${PV}.tar.gz"

S = "${WORKDIR}/aria2-${PV}"

inherit autotools gettext

LDFLAGS += "-L${STAGING_LIBDIR}"
CFLAGS += "-L${STAGING_LIBDIR} -I${STAGING_INCDIR}"


do_configure() {
	cd ${S}
	autoreconf --force --install
	oe_runconf --disable-xmltest --without-libxml2 --with-ca-bundle='/etc/ssl/certs/ca-certificates.crt' --without-openssl
}


do_compile() {
	cd ${S}
	gettextize -f
        oe_runmake CFLAGS="${CFLAGS}" LDFLAGS="${LDFLAGS}"
}

do_install() {
        mkdir -p ${D}/${bindir}
        install -m 0755 ${S}/src/aria2c ${D}/${bindir}
}
SRC_URI[md5sum] = "e6155852b035c641e6ef9cb6a414c5e1"
SRC_URI[sha256sum] = "f19311aaea414e31dc6edf641bdc43a173f9e4b0e3bae88fe80c0d62485c8abd"
