import subprocess

names = ["dms-sys-service", "dms-student",
         "dms-notice", "dms-college", "dms-auth", "dms-dorm-service", "dms-dorm","dms-repair"]
template = """
apiVersion: apps/v1
kind: Deployment
metadata:
  annotations:
    deployment.kubernetes.io/revision: '1'
  generation: 1
  labels:
    app: DMS_NAME
  name: DMS_NAME
  namespace: dms
spec:
  progressDeadlineSeconds: 600
  replicas: 1
  revisionHistoryLimit: 10
  selector:
    matchLabels:
      app: DMS_NAME
  strategy:
    rollingUpdate:
      maxSurge: 25%
      maxUnavailable: 25%
    type: RollingUpdate
  template:
    metadata:
      creationTimestamp: null
      labels:
        app: DMS_NAME
    spec:
      containers:
        - image: ghcr.io/cuit9622/DMS_NAME
          imagePullPolicy: IfNotPresent
          name: DMS_NAME
          env:
          - name: MYSQL_SERVER
            value: "dms-infra.dms.svc.cluster.local:3306"
          - name: NACOS_SERVER
            value: "dms-infra.dms.svc.cluster.local:8848"
          - name: REDIS_SERVER
            value: "dms-infra.dms.svc.cluster.local"
          resources: {}
          terminationMessagePath: /dev/termination-log
          terminationMessagePolicy: File
      dnsPolicy: ClusterFirst
      restartPolicy: Always
      schedulerName: default-scheduler
      securityContext: {}
      terminationGracePeriodSeconds: 30
"""
for name in names:

    config = template.replace("DMS_NAME", name)
    process = subprocess.Popen(
        "kubectl apply -f -", stdin=subprocess.PIPE, shell=True)
    process.stdin.write(config.encode())
    process.stdin.close()
    process.communicate()
