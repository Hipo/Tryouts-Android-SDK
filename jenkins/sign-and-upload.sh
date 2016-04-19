#!/usr/bin/env bash

function fail {
    echo "$*" >&2
    exit 1
}

function section_print {
    echo "=== $* ==="
}

export TRYOUTS_PRODUCTION_APP_ID="m3u7UXmu"
export TRYOUTS_PRODUCTION_APP_TOKEN="d3ff2df1206cad28af8b7da49e05cdb9:b8adeb074bd764df2e32ca5a6332acf5d2597e10"

section_print "Signing and uploading"

if [[ "$GIT_BRANCH" != "origin/master" ]]; then
  echo "Testing on a branch other than master ($GIT_BRANCH). No deployment will be done."
  exit 0
fi

CURRENT_TAG=`git describe --exact-match --abbrev=0 --tags`

if [[ $CURRENT_TAG == "" ]]; then
  echo "No tags found, no need for a release."
  exit 0
fi

echo "***************************"
echo "*    Generating Notes     *"
echo "***************************"

PREVIOUS_TAG=`git describe HEAD^1 --abbrev=0 --tags`
GIT_HISTORY=`git log --no-merges --format="- %s" $PREVIOUS_TAG..HEAD`

if [[ $PREVIOUS_TAG == "" ]]; then
  GIT_HISTORY=`git log --no-merges --format="- %s"`
fi

echo "Current Tag: $CURRENT_TAG"
echo "Previous Tag: $PREVIOUS_TAG"
echo "Release Notes:

$GIT_HISTORY"

# Thanks @djacobs https://gist.github.com/djacobs/2411095
# Thanks @johanneswuerbach https://gist.github.com/johanneswuerbach/5559514

OUTPUTDIR="$PWD/tryouts-sample/build/outputs/apk"

echo "***************************"
echo "*        Signing          *"
echo "***************************"

RELEASE_DATE=`date '+%Y-%m-%d %H:%M:%S'`
RELEASE_NOTES="Build: $CURRENT_TAG
Uploaded: $RELEASE_DATE

$GIT_HISTORY"

if [ ! -z "$TRYOUTS_PRODUCTION_APP_ID" ] && [ ! -z "$TRYOUTS_PRODUCTION_APP_TOKEN" ]; then
  echo ""
  echo "***************************"
  echo "* Uploading Release to Tryouts    *"
  echo "***************************"
  curl https://tryouts.io/applications/$TRYOUTS_PRODUCTION_APP_ID/upload/ \
    -F status="2" \
    -F notify="0" \
    -F notes="$RELEASE_NOTES" \
    -F build="@$OUTPUTDIR/tryouts-sample-release.apk" \
    -H "Authorization: $TRYOUTS_PRODUCTION_APP_TOKEN"
fi

#if [ ! -z "$TRYOUTS_STAGING_APP_ID" ] && [ ! -z "$TRYOUTS_STAGING_APP_TOKEN" ]; then
#  echo ""
#  echo "***************************"
#  echo "* Uploading Staging Release to Tryouts    *"
#  echo "***************************"
#  curl https://tryouts.io/applications/$TRYOUTS_STAGING_APP_ID/upload/ \
#    -F status="2" \
#    -F notify="0" \
#    -F notes="$RELEASE_NOTES" \
#    -F build="@$OUTPUTDIR/app-staging-release.apk" \
#    -H "Authorization: $TRYOUTS_STAGING_APP_TOKEN"
#fi