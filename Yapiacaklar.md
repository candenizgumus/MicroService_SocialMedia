# SOCIAL MEDIA APPLICATION

## Yapılacaklar Listesi 1

1. Dependency Management için gerekli ayarlamalar yapılmalı
2. Projede kullanacağımız sürümler önceki proje ile aynı olsun.
3. İçerisne şimdilik sadece AuthService Modülü ekleyeceğiz.
4. Auth entity olacak. (username,password,email,Role(enum , Admin, User) , activationCode, Status(Enum,
   ACTIVE,DELETED,PENDING,BANNED) )
5. Validation işlemleri için ilgili bağımlılık eklenecek. Ve Register olurken Validation işlemleri yapılacak
6. Register ve Login işlemleri olacak.
7. Dto ve Mapper kullanılacak.
8. Registerda validation kullanılmalı
9. Exceptionları uygun şekilde kullanalım.
10. JWT token mekanizmasını da kullanmalıyız. Login olan kullanıcıya token dönmeliyiz.

## Todo List 3 14.05.2024

1. NoSql ve Sql Farkı ?
   Tablo/tablolardaki ilişkileri kullanan yapısal (SQL) veritabanları, sıkı bir şemaya dayanırken, NoSQL veritabanları
   daha esnek bir yapıya sahiptir ve ilişkisel olmayan verileri işlemek için kullanılır. SQL veritabanları genellikle
   dikey ölçeklenirken, NoSQL veritabanları yatay ölçeklenir. SQL ACID özelliklerini sağlar, NoSQL ise genellikle daha
   esnek ve ölçeklenebilir bir yapı sunar, ancak bazıları ACID özelliklerinden feragat edebilir.
2. NoSql db tipleri nelerdir ? ve altındaki databaseler neler olabilir ?
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
   CAP teoremi, dağıtık veritabanı sistemlerinde üç temel gereklilik arasındaki zorunlu bir seçimi ifade eder:
   Tutarlılık (Consistency), Erişilebilirlik (Availability) ve Bölünebilirlik (Partition Tolerance). Bu teorem, bir
   dağıtık sistemde bu üç gereksinimden en fazla ikisinin aynı anda sağlanabileceğini belirtir.
4. ACID nedir ?

   ACID, ilişkisel veritabanı yönetim sistemlerinde (RDBMS) veri bütünlüğünü sağlamak için kullanılan dört temel
   özelliğin kısaltmasıdır:

Atomicity (Atomiklik): Bir işlem ya tamamen gerçekleştirilir ya da hiç gerçekleştirilmez. İşlemler parçalara ayrılmaz;
ya tamamı yapılır ya da hiçbiri yapılmaz.

Consistency (Tutarlılık): Veritabanındaki veriler, belirlenen kurallara ve bütünlük kısıtlamalarına uygun olarak her
zaman geçerli ve tutarlı olmalıdır.

Isolation (İzolasyon): Bir işlem diğer işlemlerden bağımsız olarak çalışmalıdır. Yani, bir işlem diğer işlemlerin
etkileşiminden etkilenmemelidir.

Durability (Kalıcılık): Bir işlem tamamlandıktan sonra, yapılan değişiklikler kalıcı olmalı ve veri kalıcılığını
sağlamak için veritabanında saklanmalıdır.

ACID özellikleri, veri bütünlüğünü korurken, işlemlerin güvenli ve tutarlı bir şekilde gerçekleştirilmesini sağlar.

5. AuthService de register olduktan sonra activasyon işlemleri yapılırken bu işlem UserProfile tarafına da
   yansıtılmalı. (openfeign) . UserProfile tarafında endpointte pathvariable ile authid alabilirsiniz. UserService
   kapalı veya bir nedenden işlem gerçekleşemezse authservice de de bu işlem geriye alınmalı.
6. Login olduktan sonra dönen token ile beraber UserProfile'ını güncelleyebilmeli.
7. Ama burada sadece username değiştirememeli. Onun dışındaki alanlar değişmeli. Örnek olarak: email bilgisini
   değiştirirse auth'da da değişmeli.
8. Update ederken eğer bir değer verilmiyorsa, eski değerler kalsın.
9. Authdaki silme UserProfile tarafına yansıtacak bir mekanizma geliştirin.

## Todo List 4 15.05.2024

1. @RestController ile @Controller farkı ?
    * @Controller annotasyonu genellikle HTML sayfalarını döndürmek veya View bileşenlerini yönetmek için kullanılırken,
    * @RestController annotasyonu RESTful web servislerini uygulamak için kullanılır ve HTTP yanıtlarını otomatik olarak
      serialize eder.
2. Spring Boot Uygulaması ayağa kalkarken neler oluyor ?
   Başlangıç: Uygulama başlatıldığında, Spring Boot başlangıç sınıfı (örneğin, SpringBootApplication sınıfı) yüklenir.

* Yapılandırma Okuma: Uygulama, application.properties veya application.yml gibi yapılandırma dosyalarını okur ve
  ayarları yükler. Bu dosyalar, uygulamanın çalışması için gerekli olan çeşitli ayarları içerebilir.

* Yerleşim Yapısı Taraması: Spring Boot, sınıflarınızı ve bileşenlerinizi bulmak için otomatik yapılandırılmış bileşen
  taraması gerçekleştirir. Bu, @Component, @Service, @Repository gibi Spring annotasyonlarıyla işaretlenmiş sınıfları
  bulur.

* Bean Yaratma: Uygulama, Spring konteynerine bean'leri oluşturur. Bu, @Autowired veya @ComponentScan gibi
  annotasyonlarla işaretlenmiş sınıfları ve bileşenleri içerir.

* Uygulama Başlatma: Uygulama, HTTP sunucusunu başlatır ve gelen istekleri dinlemeye başlar. Bu aşamada, yerel veya
  belirli bir bağlantı noktası üzerinden HTTP isteklerini kabul edebilir.

* Controller'ların Yüklenmesi: Spring Boot, @RestController ve @Controller annotasyonlarıyla işaretlenmiş sınıfları
  yükler. Bu sınıflar, gelen istekleri işleyen HTTP Controller'larını içerir.

* Uygulamanın Çalıştırılması: Son olarak, Spring Boot uygulamanızı başlatır ve çalıştırır. Uygulamanız, HTTP isteklerini
  işleyebilir, veritabanı işlemleri yapabilir ve diğer iş mantığını gerçekleştirebilir.

3. RestApi yazarken nelere dikkat edilmeli ?
    * Mimariye Uygunluk: RESTful mimari prensiplerine uygun olmalıdır. Bu, URI'lerin kaynakları temsil etmesi, HTTP
      metodlarının doğru şekilde kullanılması, stateless (durumsuz) olması gibi prensipleri içerir.

* Güvenlik: API, yetkilendirme ve kimlik doğrulama gibi güvenlik önlemleriyle korunmalıdır. SSL/TLS kullanımı, JWT (JSON
  Web Token) tabanlı yetkilendirme gibi güvenlik mekanizmaları uygulanabilir.

* Doğruluk ve Güvenilirlik: API, gelen isteklere doğru ve güvenilir yanıtlar vermelidir. Hatalı durumları ve hatalı
  istekleri ele alacak şekilde tasarlanmalıdır.

* Dökümantasyon: API'nin kullanımı kolay olmalı ve API'nin nasıl kullanılacağına dair ayrıntılı dökümantasyon
  sağlanmalıdır. Swagger veya OpenAPI Specification gibi araçlarla otomatik dökümantasyon oluşturulabilir.

* Performans: API'nin performansı önemlidir. İsteklerin hızlı bir şekilde işlenmesi ve yanıt verilmesi gerekmektedir.
  Performans testleri yapılmalı ve gerektiğinde iyileştirmeler yapılmalıdır.

* Hata İşleme: API, hatalı istekleri uygun şekilde işlemeli ve kullanıcıya açıklayıcı hata mesajları dönmelidir. HTTP
  durum kodları (örneğin, 404 Not Found, 400 Bad Request) doğru şekilde kullanılmalıdır.

* Versiyonlama: API versiyonlama kullanarak geriye dönük uyumluluğu sağlamak önemlidir. API'nin sürümleri birbirinden
  bağımsız olmalı ve değişiklikler geriye dönük uyumlu olmalıdır.

* Veri Formatları: API'nin döndürdüğü ve kabul ettiği veri formatları (JSON, XML gibi) uygun olmalıdır. İstemciye en
  uygun veri formatının kullanılması önemlidir.

4. Richardson Maturity Model nedir ?

* Seviye 0: Durumsuz Protokol: HTTP protokolü kullanılsa da, RPC benzeri bir yapı söz konusudur ve tek bir URI
  üzerinden tüm işlemler gerçekleştirilir.

* Seviye 1: Kaynaklar: API, kaynakları tekil URI'lerle temsil eder ve HTTP metotlarını kullanarak bu kaynaklar üzerinde
  işlem yapılır.

* Seviye 2: HTTP Metodları: API, uygun HTTP metotlarını kullanarak işlemleri gerçekleştirir (GET, POST, PUT, DELETE).

* Seviye 3: Hipermedya Kontrolü: API, kullanıcılara uygulamanın durumunu keşfetmeleri için gerekli bağlantıları sağlar
  ve HATEOAS prensibine uygun olarak etkileşim imkanı sunar.

Bu seviyeler, RESTful API'lerin ne kadar RESTful olduğunu belirlemek için kullanılır, en yüksek seviye olan 3. seviye
ise ideal olanıdır.

5. @SpringBootApplication anotasyonlu sınıfı spring package içine alırsam proje içine alırsam proje çalışır mı ?
    Çalışır
6. SistemeConfigServer Dahil Ediniz (Local ve Git(ayrı bir repo oluşturarak))
7. Bütün microserviceler yml dosyalarını bu configserverdan çekecek şekilde ayarlanmalıdır.
8. git config-server'daki bir yml'daki değişiklikten servisin haberdar olması için neler yapılabilir ?

* Webhook Kullanma: Git deposundaki değişikliklerin anında algılanması için webhook'lar kullanabilirsiniz. Webhook'lar,
  Git deposundaki herhangi bir değişiklik olduğunda belirli bir URL'ye bir HTTP isteği gönderir. Bu URL, Spring Boot
  servisinizin dinlediği ve gelen değişiklikleri işleyebileceği bir endpoint olabilir. Spring Boot servisiniz bu isteği
  alır almaz, yeni yapılandırma dosyalarını Git reposundan çekebilir ve uygulamayı yeniden başlatarak değişiklikleri
  yansıtabilir.

* Scheduled Task (Zamanlanmış Görev): Belirli aralıklarla (örneğin, her 5 dakikada bir) yapılandırma dosyalarını kontrol
  etmek için bir zamanlanmış görev (scheduled task) oluşturabilirsiniz. Bu görev, Git deposundaki yapılandırma
  dosyalarını düzenli olarak kontrol eder ve değişiklik tespit ederse uygulamayı yeniden başlatır.

* Spring Cloud Bus Kullanma: Spring Cloud Config ile birlikte Spring Cloud Bus'u kullanarak dağişiklikleri servislere
  iletebilirsiniz. Spring Cloud Bus, mikro servisler arasında mesaj iletişimi sağlayan bir kütüphanedir. Yapılandırma
  dosyalarındaki değişiklikler olduğunda, Spring Cloud Bus bu değişiklikleri alır ve servislere iletir. Servisler bu
  değişiklikleri alır almaz, yeni yapılandırma dosyalarını çeker ve uygulamayı yeniden başlatır.

## Todo List 5 16.05.2024
1. PostService yazınız. Bir user login olduktan sonra postservice aracılığı ile post atabilmelidir.
2. User Kendi attığı postları listeleyebilmeli.
3. User atılan tüm postları da görebileceği bir endpointi olmalı.
3. User kendi attığı postu güncelleyebilmeli.
4. Database i siz belirleyin. (Mongo veya PostgreSql)
5. - Optional: ApiGateway ekleyebilirsiniz.