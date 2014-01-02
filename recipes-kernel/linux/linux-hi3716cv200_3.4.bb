require linux.inc

DESCRIPTION = "Linux kernel for hi3716cv200 board"
COMPATIBLE_MACHINE = "hi3716cv200"

PV_append = "+git${SRCREV}"

SRCREV = "2a03a5453a3c446fa21b50f7ea0b914095d308e9"
SRC_URI = "git://github.com/hisilicon/linux-x5hd2.git;protocol=https"

S = "${WORKDIR}/git"

# NOTE: For now we pull in the default config from the kernel tree.
KERNEL_DEFCONFIG = "hi3716cv200_rdk_defconfig"

# CMDLINE hi3716cv200
CMDLINE_hi3716cv200 = "mem=1G console=ttyAMA0,115200"

UDEV_GE_141 ?= "1"

do_configure_prepend() {
	install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}


