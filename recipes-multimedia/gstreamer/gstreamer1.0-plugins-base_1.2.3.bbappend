
FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-plugins-base_git:"
SRC_URI_append += " \
    file://0001-gstvideodecoder.h.patch \
    file://0001-gstvideodecoder.c.patch \
    "
PACKAGE_ARCH = "${MACHINE_ARCH}"
