./gradlew clean bundleProductionRelease
bundletool build-apks --connected-device \
--bundle=./composeApp/build/outputs/bundle/productionRelease/composeApp-production-release.aab \
--output=./composeApp-production-release.apks
bundletool install-apks --apks=./composeApp-production-release.apks
rm ./composeApp-production-release.apks