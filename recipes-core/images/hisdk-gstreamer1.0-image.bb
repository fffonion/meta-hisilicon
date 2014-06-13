DESCRIPTION = "An image that will launch into the demo application for the embedded (not based on X11) version of Qt."
LICENSE = "MIT"
PR = "r3"

LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"


IMAGE_INSTALL += "\
	${CORE_IMAGE_BASE_INSTALL} \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-meta-base \
    gstreamer1.0-plugins-good-audioparsers \
    gstreamer1.0-plugins-good-isomp4 \
    libgstriff-1.0 \
    libgstrtp-1.0 \
    alsa-tools \
    alsa-utils-alsaconf \
    alsa-utils \
    alsa-lib \
    gstreamer1.0-plugins-good-deinterlace  \
"

inherit core-image

