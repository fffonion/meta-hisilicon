DISCRIPTION = "Mali GPU libraries for HI3716CV200"
LICENSE = "Proprietary"

LIC_FILES_CHKSUM = "file://LICENCE;md5=427429c3f0211c2090febdeac0f46d76"


PROVIDES = "virtual/libgles1 virtual/libgles2 virtual/egl"
COMPATIBLE_MACHINE = "hi3716cv200"

SRCREV = "4d2d07e07df4a0809c8853a7b907949f46d758c9"
SRC_URI = "git://github.com/hisilicon/x5hd2-gpu-libs.git;protocol=https"

S = "${WORKDIR}/git"

PV = "1.0+gitr${SRCPV}"

#inherit pkgconfig

do_install () {
  install -d ${D}${libdir}
  cp -a lib/* ${D}${libdir}

  install -d ${D}${includedir}
  cp -r include/* ${D}${includedir}

#  install -d ${D}${libdir}/pkgconfig
#  install -m 0644 ${WORKDIR}/egl.pc ${D}${libdir}/pkgconfig/
}

# These are proprietary binaries generated elsewhere so don't check ldflags
INSANE_SKIP_${PN} = "ldflags textrel"

FILES_${PN} = "${libdir}/libMali.so"
FILES_${PN}-dev = "${libdir}/libEGL* \
                   ${libdir}/libGLES* \
                   ${libdir}/libOpenVG* \
                   ${includedir}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
