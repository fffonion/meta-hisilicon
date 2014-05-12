PACKAGECONFIG_GL = "gles2"
PACKAGECONFIG_FB = " "
PACKAGECONFIG_append = " linuxfb icu examples"

FILESEXTRAPATHS_prepend := "${THISDIR}/qtbase:"
SRC_URI_append += " \
    file://qeglfshooks_hi3716cv200.cpp \
    "

do_configure_prepend() {
# adapt qmake.conf to our needs
sed -i 's!load(qt_config)!!' ${S}/mkspecs/linux-oe-g++/qmake.conf
cat >> ${S}/mkspecs/linux-oe-g++/qmake.conf <<EOF
EGLFS_PLATFORM_HOOKS_SOURCES = \$\$PWD/qeglfshooks_hi3716cv200.cpp
QT_QPA_DEFAULT_PLATFORM = eglfs
load(qt_config)

EOF

cp ${WORKDIR}/qeglfshooks_hi3716cv200.cpp ${S}/mkspecs/linux-oe-g++/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
