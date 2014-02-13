DESCRIPTION = "Linaro Kernel For Hisilicon hi3716-dkb board"

require linaro-kernel.inc

SRCREV = "fe0eafa5b6e7bf34ae6b2a6028bd851011578bc7"
PV = "3.14+git${SRCPV}"

KERNEL_DEVICETREE = "${S}/arch/arm/boot/dts/hi3716-dkb.dts"

BOOTARGS_COMMON = "console=ttyAMA0,115200"

SRC_URI = "git://github.com/hisilicon/linaro-kernel.git;protocol=https;branch=linux-linaro-hi3716cv200"


