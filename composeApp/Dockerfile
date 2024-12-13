FROM nginx:latest
WORKDIR /usr/share/nginx/html
COPY build/dist/wasmJs/productionExecutable .
EXPOSE 3000:3000
CMD ["nginx", "-g", "daemon off;"]
