DESCRIPTION = "Linaro Kernel For Hisilicon hi3716-dkb board"

require linaro-kernel.inc

SRCREV = "fd26efea49ea1a7e3594e446ee8648f059961f23"
PV = "3.15+git${SRCPV}"

KERNEL_DEVICETREE = "${S}/arch/arm/boot/dts/hix5hd2-dkb.dts"

BOOTARGS_COMMON = "console=ttyAMA0,115200"

SRC_URI = "git://github.com/hisilicon/linaro-kernel.git;protocol=https;branch=hix5hd2  \
           file://defconfig \
           "


