
FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-libav_1.2.3:"
SRC_URI_append += " \
    file://0001-gstavviddec.c.patch \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"
