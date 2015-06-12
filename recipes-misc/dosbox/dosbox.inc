DESCRIPTION = "Dos Emulator based on SDL"
SECTION = "base"
PRIORITY = "optional"
DEPENDS = "virtual/libsdl"
LICENSE = "GPL"

LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "\
	${SOURCEFORGE_MIRROR}/dosbox/dosbox-${PV}.tar.gz \
	file://nocdrom.patch"

CXXFLAGS_append = " -DC_SDL_NOCDROM"

inherit autotools
