
FILESEXTRAPATHS_prepend := "${THISDIR}/gst-openmax:"
SRC_URI_append += " \
    file://0001-Add-zero-copy.patch \
    file://0002-Add-Mpeg2-dec-support.patch \
    file://gst-openmax.conf \
    file://gst-openmax.sh \
    "

do_install_append () {
  install -d ${D}${sysconfdir}
  install -m 644 ${WORKDIR}/gst-openmax.conf ${D}${sysconfdir}

  install -d ${D}${sysconfdir}/profile.d/
  install -m 0755 ${WORKDIR}/gst-openmax.sh ${D}${sysconfdir}/profile.d/gst-openmax.sh
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
