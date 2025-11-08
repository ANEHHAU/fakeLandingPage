# Sử dụng image Java ổn định
FROM eclipse-temurin:17-jdk

# Đặt thư mục làm việc
WORKDIR /app

# Copy toàn bộ mã nguồn
COPY . .

# ✅ Cấp quyền chạy cho gradlew
RUN chmod +x ./gradlew

# Build project bằng Gradle (bỏ qua test để build nhanh hơn)
RUN ./gradlew clean build -x test

# Chạy file jar đã build
CMD ["java", "-jar", "build/libs/landingpage-0.0.1-SNAPSHOT.jar"]
