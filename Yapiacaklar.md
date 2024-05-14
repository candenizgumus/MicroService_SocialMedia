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
2. NoSql db tipleri nelerdir  ? ve altındaki databaseler neler olabilir ? 
3. CAP teoremi nedir ?
4. ACID nedir ?
5. AuthService de register olduktan sonra activasyon işlemleri yapılırken bu işlem UserProfile tarafına da yansıtılmalı. (openfeign) . UserProfile tarafında endpointte pathvariable ile authid alabilirsiniz. UserService kapalı veya bir nedenden işlem gerçekleşemezse authservice de de bu işlem geriye alınmalı.
6. Login olduktan sonra dönen token ile beraber UserProfile'ını güncelleyebilmeli.
7. Ama burada sadece username değiştirememeli. Onun dışındaki alanlar değişmeli. Örnek olarak: email bilgisini değiştirirse auth'da da değişmeli.
8. Update ederken eğer bir değer verilmiyorsa, eski değerler kalsın.
9. Authdaki silme UserProfile tarafına yansıtacak bir mekanizma geliştirin.

