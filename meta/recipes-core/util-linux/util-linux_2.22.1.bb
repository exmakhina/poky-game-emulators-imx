MAJOR_VERSION = "2.22"
PR = "r3"
require util-linux.inc

# note that `lscpu' is under GPLv3+
LICENSE_${PN}-lscpu = "GPLv3+"

SRC_URI += "file://util-linux-ng-replace-siginterrupt.patch \
            file://util-linux-ng-2.16-mount_lock_path.patch \
            file://uclibc-__progname-conflict.patch \
	    file://configure-sbindir.patch \
	    file://fix-configure.patch \
"

SRC_URI[md5sum] = "cad23c41a014af766d467b86628bd0fd"
SRC_URI[sha256sum] = "4297ea2a560f8839d7b3cde72a5955d1458dbad38fe0e3d99ccb1fde3df751b2"

# Only lscpu part is gplv3; rest of the code is not, 
# so take out the lscpu parts while running non-gplv3 build.
# The removal of the package should now occur during
# the build if INCOMPATIBLE_LICENSE is set to GPLv3

python () {
    d.setVar("REMOVELSCPU", "no")
    if (d.getVar("INCOMPATIBLE_LICENSE", True) or "").find("GPLv3") != -1:
        # avoid GPLv3
        d.setVar("REMOVELSCPU", "yes")
        src_uri = (d.getVar("SRC_URI", False) or "").split()
        src_uri.append("file://remove-lscpu.patch")
        d.setVar("SRC_URI", " ".join(src_uri))
}       

do_remove_lscpu() {
    if [ "x${REMOVELSCPU}" = "xyes" ]; then
         rm -f sys-utils/lscpu.c sys-utils/lscpu.1
         rm -rf tests/ts/lscpu tests/expected/lscpu
    fi
}

addtask remove_lscpu before do_configure after do_patch

CACHED_CONFIGUREVARS += "scanf_cv_alloc_modifier=as"
EXTRA_OECONF_class-native += "--disable-fallocate --disable-use-tty-group"
