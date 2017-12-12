require linux.inc

DESCRIPTION = "Linux kernel for hi3716cv200 board"
COMPATIBLE_MACHINE = "hi3716cv200"

LINUX_VERSION ?= "4.13.10"
AUFS_VERSION = "4.13"

PV = "${LINUX_VERSION}"

PR = "r0"

SRC_URI = "https://cdn.kernel.org/pub/linux/kernel/v4.x/linux-${PV}.tar.xz\
    git://github.com/sfjro/aufs4-standalone.git;protocol=https;rev=aufs${AUFS_VERSION};branch=aufs${AUFS_VERSION};destsuffix=git/aufs4-standalone\
    file://defconfig \
" 
SRC_URI[md5sum] = "5c3fcea00305c468f5cd5d0779f51820"
SRC_URI[sha256sum] = "dd3090ac4fd9d6febfe0474a7c18a7dfd8cb84cc520310cf87747a483cd86330"

S = "${WORKDIR}/linux-${LINUX_VERSION}"

# NOTE: For now we pull in the default config from the kernel tree.
# KERNEL_DEFCONFIG = "hisi_defconfig"

# CMDLINE hi3716cv200
CMDLINE_hi3716cv200 = "mem=1G console=ttyAMA0,115200"

UDEV_GE_141 ?= "1"

do_configure_prepend() {
	# install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}


