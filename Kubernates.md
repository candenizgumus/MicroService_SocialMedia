# KUBECTL KOMUTLARI

## kubectl get pods
pods ları getirir.

## kubectl get nodes
nodelarla ilgili genel bilgileri getirir.

## kubectl get nodes -o wide
bir öncekki komuta ek olarak nude un internal ip ve external ip bilgilerini getirir.

## Docker a postgresql image kurma komutu

 ```
 docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres
 ```

## k8s Cluster içindeki bir poda posgressql image kurma komutu

 ```
 kubectl run java14-postgresql --env="POSTGRES_PASSWORD=root" --port=5432 --image=postgres
 ```

## Podları silmek için komut

```
kubectl delete pods java14-postgresql
```

## kubectl get pods -o wide
kubectl get pods komutuna ek olarak ip bilgisi ve pod'un hangi nodei çinde olduğu bilgisini döner.

## kubectl describe pod java14-postgresql
Bu komutla pod hakkında tüm detaylı bilgilere erişilir. Ayrıca events kısmından podda oluşaner olay log oalrak izlenebilir. Bir hata durumunda buradan kontrol yapılabilir..
## kubectl get pods -w
bu komutla cluster içindeki tüm podların anlık durumları gözlenebilir.
Ctrl + C ile bu koumutu durdurabiliriz.

## kubectl apply -f pod postgresql.yml
yml dosyası aracılığı ile bir pod ayağa kaldırmak için kullanılan komutttur.

## pod_postgresql.yml
Aşağıdaki dosya aracılığıyla bir postgresql image içeren pod ayağa kaldırabilir.

```yml
apiVersion: v1
kind: Pod
metadata:
  name: pod-postgre
  labels: 
   team: developer-postgre

spec:
 containers:
  - name: container-postgre
    image:  postgres
    resources: 
     limits:
      cpu: "400m"
      memory: "1024Mi"
    ports:
    - containerPort : 5432
    env:
    - name: POSTGRES_PASSWORD
      value: root 

   
```
## deployment yapmak için
```angular2html
apiVersion: apps/v1
kind: Deployment
metadata:
  name: deployment-postgre
spec:
  selector:
    matchLabels:
      team: developer-postgre
  replicas: 1
  template:
    metadata:
      name: pod-postgre
      labels:
        team: developer-postgre
    spec:
      containers:
      - name: container-postgre
        image:  postgres
        resources:
          limits:
            cpu: "400m"
            memory: "1024Mi"
        ports:
        - containerPort: 5432
        env:
        - name: POSTGRES_PASSWORD
          value: root

```

## kubectl apply -f service_loadbalancer_postgresql.yml
yml dosyası aracılığı ile bir service objesi ayağa kaldırmak için yazılan komut.
```angular2html
kind: Service
apiVersion: v1
metadata:
  name:  loadbalancer-postgresql
spec:
  selector:
    team: developer-postgre
  type:  LoadBalancer
  ports:
  - name:  postgreports
    port:  11111
    targetPort:  5432
```

## kubectl get services
bu komutla sistemdeki service objelerinin (loadbalancer,clusterip gibi) ay

## kubectl exec -it deployment-postgre-865bd8cf76-h26xq -- bash

postgressql pod'unun bash'ine bağlanıyor. -it = in terminal
 * su postgres
 * psql
   * CREATE DATABASE deneme;
   * \l ters slash ile databaseler listelenir
   * quit 
 * exit
