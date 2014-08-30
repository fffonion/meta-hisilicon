SUMMARY = "Hisilicon mmz library"
DESCRIPTION = "Hisilicon's SDK mmz module"
SECTION = "multimedia"
LICENSE = "LGPLv2"

PV = "1.0+gitr${SRCPV}"
SRCREV = "ecb57d4d54a144cbdd84f493a98c1fb1eec2ee67"
SRC_URI = "git://github.com/hisilicon/hix5hd2-mmz-lib.git;protocol=https"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

FILES_${PN} += " ${libdir}/*mmz*.so"
FILES_${PN}-dev += " ${libdir}/*mmz*.la ${libdir}/*mmz*.a"
FILES_${PN}-dbg += " ${libdir}/.debug/ ${libexecdir}/.debug/"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

PACKAGE_ARCH = "${MACHINE_ARCH}"

