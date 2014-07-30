DESCRIPTION = "Linaro Kernel For Hisilicon hi3716-dkb board"

require linaro-kernel.inc

SRCREV = "87c8c10aff5db65eac368940d6558a193af971bd"
PV = "3.15+git${SRCPV}"

KERNEL_DEVICETREE = "${S}/arch/arm/boot/dts/hix5hd2-dkb.dts"

BOOTARGS_COMMON = "console=ttyAMA0,115200"

SRC_URI = "git://github.com/hisilicon/linaro-kernel.git;protocol=https;branch=xjc/hix5hd2-lsk-3.10-msp  \
           file://defconfig \
           "


