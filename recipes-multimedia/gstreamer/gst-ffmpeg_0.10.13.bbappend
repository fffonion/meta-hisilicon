FILESEXTRAPATHS_prepend := "${THISDIR}/gst-ffmpeg:"
SRC_URI_append += " \
    file://0001-Raise-the-rank-for-mp3-ac3-eac3-dts-decoder.patch \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"
