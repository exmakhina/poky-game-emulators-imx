# The Boost web site provides free peer-reviewed portable
# C++ source libraries. The emphasis is on libraries which
# work well with the C++ Standard Library. The libraries are
# intended to be widely useful, and are in regular use by
# thousands of programmers across a broad spectrum of applications.
HOMEPAGE = "http://www.boost.org/"
LICENSE = "BSL-1.0 & MIT & Python-2.0"
LIC_FILES_CHKSUM = "file://LICENSE_1_0.txt;md5=e4224ccaecb14d942c71d31bef20d78c"

BOOST_VER = "${@"_".join(d.getVar("PV",1).split("."))}"
BOOST_MAJ = "${@"_".join(d.getVar("PV",1).split(".")[0:2])}"
BOOST_P = "boost_${BOOST_VER}"

SRC_URI = "${SOURCEFORGE_MIRROR}/boost/${BOOST_P}.tar.bz2"

SRC_URI[md5sum] = "1be49befbdd9a5ce9def2983ba3e7b76"
SRC_URI[sha256sum] = "910c8c022a33ccec7f088bd65d4f14b466588dda94ba2124e78b8c57db264967"

S = "${WORKDIR}/${BOOST_P}"
