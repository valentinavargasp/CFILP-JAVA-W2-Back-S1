# CFILP-JAVA-W2-Back-S1
Repositorio Back Squad 1 | Java | CFI LP 25

# AlkyWallet - Backend 🔐

Este repositorio contiene la lógica del servidor y la API REST para **AlkyWallet**, una billetera virtual que permite la gestión de cuentas, usuarios, transacciones y seguridad de acceso. Desarrollado como parte del programa de Aceleración Tech Track Java, SQL y JavaScript propuesto por Agencia CITIA – Alkemy.

## 🔧 Tecnologías

- Java 17
- Spring Boot 3.x
- Spring Security + JWT
- Hibernate + JPA
- MySQL
- MapStruct (para mapeo DTO ↔ Entity)
- Swagger/OpenAPI
- JUnit

## 🧩 Arquitectura

Implementamos el patrón **MVC**:
- `Controllers`: Manejan endpoints HTTP.
- `Services`: Contienen la lógica de negocio.
- `Repositories`: Interacción con la base de datos.
- `DTOs`: Objetos de transferencia para encapsular y proteger datos.
- `Mappers`: Conversión entre entidades y DTOs.

## 🌐 Endpoints principales

- `/api/auth/login` → Autenticación y emisión de JWT
- `/api/users` → CRUD de usuarios
- `/api/accounts` → Gestión de cuentas
- `/api/transactions` → Simulación de transacciones

Consulta la [documentación Swagger](http://localhost:8080/swagger-ui.html) (una vez corriendo) para explorar todos los endpoints.

## 🚀 Cómo ejecutar

1. Clona el repositorio:
```bash
git clone https://github.com/alkemyTech/CFILP-JAVA-W2-Back-S1.git
```

2. Configura tu base de datos en `application.properties`.

3. Ejecuta la aplicación:
```bash
./mvnw spring-boot:run
```

4. Accede a `http://localhost:8080`.

## 📁 Estructura de paquetes

```plaintext
com.alkemy.wallet
├── auth
├── config
├── controllers
├── dto
├── exceptions
├── mapper
├── models
├── repository
├── security
├── services
└── utils
```

## 🧑‍💻 Desarrollado por

| Nombre | Rol | GitHub | Correo Electrónico |
|--------|-----|--------|---------------------|
| Sebastián Tesitore | Fullstack | [GitHub](https://github.com/teshy18) | sebas.tesitore@gmail.com |
| Valentina Vargas Pescara | Fullstack| [GitHub](https://github.com/valentinavargasp) | valentinapescarav@gmail.com |


## 🌈 Cómo Contribuir

¡Damos la bienvenida a las contribuciones de la comunidad! Si deseas contribuir, sigue estos pasos:

1. Haz un fork del repositorio
2. Crea tu rama de características (`git checkout -b feature/CaracteristicaIncreible`)
3. Haz commit de tus cambios (`git commit -m 'Añadir alguna CaracteristicaIncreible'`)
4. Haz push a la rama (`git push origin feature/CaracteristicaIncreible`)
5. Abre un Pull Request