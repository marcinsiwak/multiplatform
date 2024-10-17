./gradlew clean bundleProductionRelease
bundletool build-apks --connected-device --bundle=./androidApp/build/outputs/bundle/productionRelease/app-production-release.aab --output=./app-production-release.apks
bundletool install-apks --apks=./app-production-release.apks
rm ./app-production-release.apks