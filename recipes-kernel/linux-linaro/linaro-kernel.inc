DESCRIPTION = "Linaro Kernel Basic support recipe"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel siteinfo
require recipes-kernel/linux/linux-dtb.inc

SRCREV = "${AUTOREV}"
SRCREV_FORMAT = "kernel"
S = "${WORKDIR}/git"

COMPATIBLE_HOST = "arm.*"
KERNEL_IMAGETYPE = "zImage"

do_configure_prepend_arm() {
    ARCH=arm scripts/kconfig/merge_config.sh -m arch/arm/configs/multi_v7_defconfig \
      linaro/configs/hi3xxx.conf
}

do_configure_prepend_armeb() {
    ARCH=arm scripts/kconfig/merge_config.sh -m arch/arm/configs/multi_v7_defconfig \
      linaro/configs/hi3xxx.conf
}

