# SOCIAL MEDIA APPLICATION
## Yapılacaklar Listesi 1
1. Dependency Management için gerekli ayarlamalar yapılmalı
2. Projede kullanacağımız sürümler önceki proje ile aynı olsun.
3. İçerisne şimdilik sadece AuthService Modülü ekleyeceğiz.
4. Auth entity olacak. (username,password,email,Role(enum , Admin, User) , activationCode, Status(Enum, ACTIVE,DELETED,PENDING,BANNED) )
5. Validation işlemleri için ilgili bağımlılık eklenecek. Ve Register olurken Validation işlemleri yapılacak
6. Register ve Login işlemleri olacak.
7. Dto ve Mapper kullanılacak.
8. Registerda validation kullanılmalı
9. Exceptionları uygun şekilde kullanalım.
10. JWT token mekanizmasını da kullanmalıyız. Login olan kullanıcıya token dönmeliyiz.

## Todo List 3 14.05.2024

1. NoSql ve Sql Farkı ?
   Tablo/tablolardaki ilişkileri kullanan yapısal (SQL) veritabanları, sıkı bir şemaya dayanırken, NoSQL veritabanları daha esnek bir yapıya sahiptir ve ilişkisel olmayan verileri işlemek için kullanılır. SQL veritabanları genellikle dikey ölçeklenirken, NoSQL veritabanları yatay ölçeklenir. SQL ACID özelliklerini sağlar, NoSQL ise genellikle daha esnek ve ölçeklenebilir bir yapı sunar, ancak bazıları ACID özelliklerinden feragat edebilir.
2. NoSql db tipleri nelerdir  ? ve altındaki databaseler neler olabilir ?
   NoSQL veritabanı tipleri :

Anahtar-Değer Mağazaları (Key-Value Stores)
Sütun tabanlı (Columnar Stores)
Belge tabanlı (Document Stores)
Grafik tabanlı (Graph Databases)
Bu tiplerin altında bulunan bazı popüler veritabanları şunlardır:

Anahtar-Değer Mağazaları: Redis, Riak
Sütun tabanlı: Apache Cassandra, HBase
Belge tabanlı: MongoDB, Couchbase
Grafik tabanlı: Neo4j, Amazon Neptune

3. CAP teoremi nedir ?
   CAP teoremi, dağıtık veritabanı sistemlerinde üç temel gereklilik arasındaki zorunlu bir seçimi ifade eder: Tutarlılık (Consistency), Erişilebilirlik (Availability) ve Bölünebilirlik (Partition Tolerance). Bu teorem, bir dağıtık sistemde bu üç gereksinimden en fazla ikisinin aynı anda sağlanabileceğini belirtir.
4. ACID nedir ?

   ACID, ilişkisel veritabanı yönetim sistemlerinde (RDBMS) veri bütünlüğünü sağlamak için kullanılan dört temel özelliğin kısaltmasıdır:

Atomicity (Atomiklik): Bir işlem ya tamamen gerçekleştirilir ya da hiç gerçekleştirilmez. İşlemler parçalara ayrılmaz; ya tamamı yapılır ya da hiçbiri yapılmaz.

Consistency (Tutarlılık): Veritabanındaki veriler, belirlenen kurallara ve bütünlük kısıtlamalarına uygun olarak her zaman geçerli ve tutarlı olmalıdır.

Isolation (İzolasyon): Bir işlem diğer işlemlerden bağımsız olarak çalışmalıdır. Yani, bir işlem diğer işlemlerin etkileşiminden etkilenmemelidir.

Durability (Kalıcılık): Bir işlem tamamlandıktan sonra, yapılan değişiklikler kalıcı olmalı ve veri kalıcılığını sağlamak için veritabanında saklanmalıdır.

ACID özellikleri, veri bütünlüğünü korurken, işlemlerin güvenli ve tutarlı bir şekilde gerçekleştirilmesini sağlar.

5. AuthService de register olduktan sonra activasyon işlemleri yapılırken bu işlem UserProfile tarafına da yansıtılmalı. (openfeign) . UserProfile tarafında endpointte pathvariable ile authid alabilirsiniz. UserService kapalı veya bir nedenden işlem gerçekleşemezse authservice de de bu işlem geriye alınmalı.
6. Login olduktan sonra dönen token ile beraber UserProfile'ını güncelleyebilmeli.
7. Ama burada sadece username değiştirememeli. Onun dışındaki alanlar değişmeli. Örnek olarak: email bilgisini değiştirirse auth'da da değişmeli.
8. Update ederken eğer bir değer verilmiyorsa, eski değerler kalsın.
9. Authdaki silme UserProfile tarafına yansıtacak bir mekanizma geliştirin.


## Todo List 4 15.05.2024
1. @RestController ile @Controller farkı ?
2. Spring Boot Uygulaması ayağa kalkarken neler oluyor ?
3. RestApi yazarken nelere dikkat edilmeli ?
4. Richardson Maturity Model nedir ?
5. @SpringBootApplication anotasyonlu sınıfı spring package içine alırsam proje içine alırsam proje çalışır mı ?
6. SistemeConfigServer Dahil Ediniz (Local ve Git(ayrı bir repo oluşturarak))
7. Bütün microserviceler yml dosyalarını bu configserverdan çekecek şekilde ayarlanmalıdır.
8. git config-server'daki bir yml'daki değişiklikten servisin haberdar olması için neler yapılabilir ?

* Webhook Kullanma: Git deposundaki değişikliklerin anında algılanması için webhook'lar kullanabilirsiniz. Webhook'lar, Git deposundaki herhangi bir değişiklik olduğunda belirli bir URL'ye bir HTTP isteği gönderir. Bu URL, Spring Boot servisinizin dinlediği ve gelen değişiklikleri işleyebileceği bir endpoint olabilir. Spring Boot servisiniz bu isteği alır almaz, yeni yapılandırma dosyalarını Git reposundan çekebilir ve uygulamayı yeniden başlatarak değişiklikleri yansıtabilir.

* Scheduled Task (Zamanlanmış Görev): Belirli aralıklarla (örneğin, her 5 dakikada bir) yapılandırma dosyalarını kontrol etmek için bir zamanlanmış görev (scheduled task) oluşturabilirsiniz. Bu görev, Git deposundaki yapılandırma dosyalarını düzenli olarak kontrol eder ve değişiklik tespit ederse uygulamayı yeniden başlatır.

* Spring Cloud Bus Kullanma: Spring Cloud Config ile birlikte Spring Cloud Bus'u kullanarak dağişiklikleri servislere iletebilirsiniz. Spring Cloud Bus, mikro servisler arasında mesaj iletişimi sağlayan bir kütüphanedir. Yapılandırma dosyalarındaki değişiklikler olduğunda, Spring Cloud Bus bu değişiklikleri alır ve servislere iletir. Servisler bu değişiklikleri alır almaz, yeni yapılandırma dosyalarını çeker ve uygulamayı yeniden başlatır.
