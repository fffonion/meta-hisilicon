
FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0_git:"
SRC_URI_append += " \
    file://0001-gstbufferpool.c.patch \
    file://0001-gstbufferpool.h.patch \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"
