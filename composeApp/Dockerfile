FROM nginx:latest
EXPOSE 3000:3000
WORKDIR /usr/share/nginx/html
COPY build/dist/wasmJs/productionExecutable .
CMD ["nginx", "-g", "daemon off;"]
