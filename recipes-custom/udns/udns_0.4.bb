DESCRIPTION = "DNS Resolver Library"
HOMEPAGE = "http://www.corpit.ru/mjt/udns.html"
LICENSE = "LGPL"
PR = "r0"

SRC_URI = "\
        http://www.corpit.ru/mjt/udns/udns-${PV}.tar.gz;name=udns \
        file://fix-cc-check.patch;apply=yes \
"

SRC_URI[udns.md5sum] = "51e141b044b078d71ebb71f823959c1b"
SRC_URI[udns.sha256sum] = "115108dc791a2f9e99e150012bcb459d9095da2dd7d80699b584ac0ac3768710"

S = "${WORKDIR}/${PN}-${PV}"

TARGET_CC_ARCH += "${LDFLAGS}"

# Package is using configure and Makefile.in, but not autotools in general ...

do_configure() {
        ./configure --disable-ipv6
}

do_compile() {
    oe_runmake shared static
}

do_install() {
    oe_libinstall -so -a libudns ${D}${libdir}
    ln -s libudns.so.0 ${D}${libdir}/libudns_s.so
    install -d ${D}${includedir}
    install -m 0644 ${S}/udns.h ${D}${includedir}
}
