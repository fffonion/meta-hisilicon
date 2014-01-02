DESCRIPTION = "gstreamer hisilicon video sink"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

DEPENDS = "virtual/driverlibs-rdk-hisilicon gstreamer gst-plugins-base"

PV = "1.0+gitr${SRCPV}"

SRCREV = "8a43e73dd97c2264c8f09d2dc313276fb9211d2c"
SRC_URI = "git://github.com/hisilicon/gst-hivosink.git;protocol=https"

S = "${WORKDIR}/git"

inherit autotools

FILES_${PN} += "${libdir}/gstreamer-0.10/libgst*.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgst*.la"
FILES_${PN}-staticdev += "${libdir}/gstreamer-0.10/libgst*.a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"

PACKAGE_ARCH = "${MACHINE_ARCH}"
