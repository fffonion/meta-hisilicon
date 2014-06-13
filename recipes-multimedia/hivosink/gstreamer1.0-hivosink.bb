CRIPTION = "gstreamer hisilicon video sink"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=e431e272f5b8a6a4f948a910812f235e"

DEPENDS = "virtual/driverlibs-rdk-hisilicon gstreamer1.0 gstreamer1.0-plugins-base"

PV = "1.0+gitr${SRCPV}"

SRCREV = "b0ca4200971cbdd156a01d5ba24806aa0e78e6ba"
SRC_URI = "git://github.com/hisilicon/gstreamer1.0-hivosink.git;protocol=https"

S = "${WORKDIR}/git"

inherit autotools
FILES_${PN} += "${libdir}/gstreamer-1.0/*.so"
FILES_${PN}-dbg += "${libdir}/gstreamer-1.0/.debug"
FILES_${PN}-dev += "${libdir}/gstreamer-1.0/*.la"
FILES_${PN}-staticdev += "${libdir}/gstreamer-1.0/*.a"

PACKAGE_ARCH = "${MACHINE_ARCH}"
