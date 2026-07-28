node('linux') {
  stage ('Poll') {
    checkout([
      $class: 'GitSCM', branches: [[name: '*/main']], extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/mmh3port.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [
      string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/mmh3port.git'),
      string(name: 'PORT_DESCRIPTION', value: 'Python extension for MurmurHash (MurmurHash3), a set of fast and robust hash functions.'),
      string(name: 'BUILD_LINE', value: 'DEV'),
      booleanParam(name: 'PUBLISH_PYTHON_WHEEL', value: true)
    ]
  }
}
