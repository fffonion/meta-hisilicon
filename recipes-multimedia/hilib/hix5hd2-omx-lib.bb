SUMMARY = "Hisilicon video output library"
DESCRIPTION = "Hisilicon's SDK vo module"
SECTION = "multimedia"
LICENSE = "LGPLv2"

PV = "1.0+gitr${SRCPV}"
SRCREV = "7c4b473ffeb7894c310fbe5d33af166d844cd6b6"
SRC_URI = "git://github.com/hisilicon/hix5hd2-omx-lib.git;protocol=https"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

FILES_${PN} += " ${libdir}/*vou*.so"
FILES_${PN}-dev += " ${libdir}/*vou*.la ${libdir}/*vou*.a"
FILES_${PN}-dbg += " ${libdir}/.debug/ ${libexecdir}/.debug/"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

PACKAGE_ARCH = "${MACHINE_ARCH}"

