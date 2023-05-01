import subprocess
subprocess.Popen('/bin/ls %s' % ('something',), shell=True)
