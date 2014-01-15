DESCRIPTION = "An image that will launch into the demo application for the embedded (not based on X11) version of Qt."
LICENSE = "MIT"
PR = "r3"

LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"


IMAGE_INSTALL += "\
	${CORE_IMAGE_BASE_INSTALL} \
    gst-fluendo-mpegdemux \
    gst-meta-base \
	gst-meta-audio \
	gst-meta-video \
    gst-plugins-good-audioparsers \
    gst-plugins-good-isomp4 \
"

inherit core-image

