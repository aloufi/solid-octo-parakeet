FROM nginx

RUN rm -rf /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
COPY dist/ /usr/share/nginx/html

CMD ["nginx", "-g", "daemon off;"]

#npm run-script build --prod
