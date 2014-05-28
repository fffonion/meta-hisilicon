DESCRIPTION = "Linaro Kernel For Hisilicon hi3716-dkb board"

require linaro-kernel.inc

SRCREV = "522e82b2461898d562045367c939904887876d88"
PV = "3.15+git${SRCPV}"

KERNEL_DEVICETREE = "${S}/arch/arm/boot/dts/hix5hd2-dkb.dts"

BOOTARGS_COMMON = "console=ttyAMA0,115200"

SRC_URI = "git://github.com/hisilicon/linaro-kernel.git;protocol=https;branch=hix5hd2  \
           file://defconfig \
           "


