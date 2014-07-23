
FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-omx_git:"
SRC_URI_append += " \
    file://0001-gstomxvideodec.c.patch \
    file://0001-gstomxh264dec.c.patch \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"
