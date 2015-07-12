TARGETS = mountkernfs.sh hostname.sh udev mountdevsubfs.sh networking hwclock.sh checkroot.sh urandom mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh kmod bootmisc.sh checkfs.sh checkroot-bootclean.sh udev-finish procps
INTERACTIVE = udev checkroot.sh checkfs.sh
udev: mountkernfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
networking: mountkernfs.sh mountall.sh mountall-bootclean.sh urandom procps
hwclock.sh: mountdevsubfs.sh
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh
urandom: mountall.sh mountall-bootclean.sh hwclock.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh
mountall-bootclean.sh: mountall.sh
mountnfs.sh: mountall.sh mountall-bootclean.sh networking
mountnfs-bootclean.sh: mountall.sh mountall-bootclean.sh mountnfs.sh
kmod: checkroot.sh
bootmisc.sh: mountall-bootclean.sh checkroot-bootclean.sh mountnfs-bootclean.sh mountall.sh mountnfs.sh udev
checkfs.sh: checkroot.sh
checkroot-bootclean.sh: checkroot.sh
udev-finish: udev mountall.sh mountall-bootclean.sh
procps: mountkernfs.sh mountall.sh mountall-bootclean.sh udev
