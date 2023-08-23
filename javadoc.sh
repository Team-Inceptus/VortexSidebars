#!/bin/bash

git config --local user.email "action@github.com"
git config --local user.name "GitHub Actions"
git fetch origin gh-pages

rm -rf docs/
mkdir ./docs

cp -R target/apidocs/* docs/

git switch -f gh-pages

for dir in ./*
do
  if [ "$dir" == "./docs" ]; then
    continue
  fi

  rm -rf "$dir"
done

cp -Rfv ./docs/* ./
rm -rf ./docs

echo "vortexsidebars.teaminceptus.us" > CNAME

git add .
git commit -m "Update JavaDocs ($1)"
git push -f origin gh-pages