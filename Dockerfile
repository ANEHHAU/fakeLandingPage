# Sử dụng image Java chính thức
FROM eclipse-temurin:17-jdk


# Đặt thư mục làm việc trong container
WORKDIR /app

# Copy toàn bộ mã nguồn vào container
COPY . .

# Build project bằng Gradle (bỏ qua test để build nhanh hơn)
RUN ./gradlew clean build -x test

# Chạy ứng dụng với file .jar chính thức
CMD ["java", "-jar", "build/libs/landingpage-0.0.1-SNAPSHOT.jar"]
