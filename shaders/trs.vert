#version 420

layout(location = 0) in vec3 vertex_position;

uniform mat4 trs;
uniform mat4 proj;

void main() {
  gl_Position = proj*trs*vec4(vertex_position, 1.0);
}