(ns livedemo.vars
  (require [clojure.string :as s]))

(def output "     UID   PID  PPID  C    STIME TTY       TIME COMMAND
      root     0     0  0  Mar 27  ?        13:36 swapper
      root     1     0  0  Mar 27  ?         0:59 init
      root  1171     1  0  Mar 27  ?         1:10 /usr/sbin/utmpd
      root   193     0  0  Mar 27  ?         0:00 rng
      root  1275  1255  0  Mar 27  ?         0:00 /usr/sbin/evmchmgr -l /var/evm/adm/logfiles/evmchmgr.log
      root   354     1  0  Mar 27  ?         0:00 /sbin/fs/fsdaemon -f 0
      root  2749  2746  0  Mar 27  ?         0:01 /opt/wbem/lbin/cimprovagt 0 4 9 root HPUXRAIDSANativeIndicationProviderModule
      root  2762  2746  0  Mar 27  ?         1:44 /opt/wbem/lbin/cimprovagt 0 4 9 root HPUXLANIndicationProviderModule
      root  2764  2746  0  Mar 27  ?         2:27 /opt/wbem/lbin/cimprovagt 0 4 9 root HPUXStorageNativeProviderModule
  loliveir 21001 20995  0 19:40:10 ?         0:00 sar -uM 6 45
     sfmdb  2799  1596  0  Mar 27  ?         0:01 postgres: sfmdb RAIDSAPROVDB [local] idle
  sophosav  5973  5971  0  Apr 16  ?         4:28 mrouter -config router.config -ORBListenEndpoints iiop://:8193/ssl_port=8194 -name Router -certStore ./Certs.reg
    ")

(def header-str (first (clojure.string/split-lines (s/trim output))))
(def data (rest (clojure.string/split-lines (s/trim output))))
(def header [:uid :pid :ppid :c :stime :tty :time :command])

(def data-row "loliveir 21001 20995  0 19:40:10 ?         0:00 sar -uM 6 45")
(def splited-row ["loliveir" "21001" "20995" "0" "19:40:10" "?" "0:00" "sar -uM 6 45"])

(def transformed-data '(["root" 0 0 0 "Mar 27" "?" "13:36" "swapper"] ["root" 1 0 0 "Mar 27" "?" "0:59" "init"] ["root" 1171 1 0 "Mar 27" "?" "1:10" "/usr/sbin/utmpd"] ["root" 193 0 0 "Mar 27" "?" "0:00" "rng"] ["root" 1275 1255 0 "Mar 27" "?" "0:00" "/usr/sbin/evmchmgr -l /var/evm/adm/logfiles/evmchmgr.log"] ["root" 354 1 0 "Mar 27" "?" "0:00" "/sbin/fs/fsdaemon -f 0"] ["root" 2749 2746 0 "Mar 27" "?" "0:01" "/opt/wbem/lbin/cimprovagt 0 4 9 root HPUXRAIDSANativeIndicationProviderModule"] ["root" 2762 2746 0 "Mar 27" "?" "1:44" "/opt/wbem/lbin/cimprovagt 0 4 9 root HPUXLANIndicationProviderModule"] ["root" 2764 2746 0 "Mar 27" "?" "2:27" "/opt/wbem/lbin/cimprovagt 0 4 9 root HPUXStorageNativeProviderModule"] ["loliveir" 21001 20995 0 "19:40:10" "?" "0:00" "sar -uM 6 45"] ["sfmdb" 2799 1596 0 "Mar 27" "?" "0:01" "postgres: sfmdb RAIDSAPROVDB [local] idle"] ["sophosav" 5973 5971 0 "Apr 16" "?" "4:28" "mrouter -config router.config -ORBListenEndpoints iiop://:8193/ssl_port=8194 -name Router -certStore ./Certs.reg"]))
(def transformed-data-row ["loliveir" 21001 20995 0 "19:40:10" "?" "0:00" "sar -uM 6 45"])

(def args [  [:pid [:int "21001"]] [:ppid [:int "20995"]] [:c [:int "0"]] [:stime [:stime_time "19:40:10"]] [:tty "?"] [:time "0:00"] [:cmd "sar -uM 6 45"]])
