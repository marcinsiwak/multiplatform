FROM nginx:latest
WORKDIR /usr/share/nginx/html
COPY build/dist/wasmJs/productionExecutable .
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
