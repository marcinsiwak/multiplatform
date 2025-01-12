FROM nginx:latest
EXPOSE 3000:3000
WORKDIR /usr/share/nginx/html
COPY ./build/dist/wasmJs/productionExecutable/ .
COPY nginx.conf /etc/nginx/conf.d/default.conf
CMD ["nginx", "-g", "daemon off;"]