SUMMARY = "Hisilicon video output library"
DESCRIPTION = "Hisilicon's SDK vo module"
SECTION = "multimedia"
LICENSE = "LGPLv2"

PV = "1.0+gitr${SRCPV}"
SRCREV = "bfbcb3b6d17506b3844d1f061ba2d030d2b6281b"
SRC_URI = "git://github.com/hisilicon/hix5hd2-vo-lib.git;protocol=https"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

FILES_${PN} += " ${libdir}/*vou*.so"
FILES_${PN}-dev += " ${libdir}/*vou*.la ${libdir}/*vou*.a"
FILES_${PN}-dbg += " ${libdir}/.debug/ ${libexecdir}/.debug/"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

PACKAGE_ARCH = "${MACHINE_ARCH}"

