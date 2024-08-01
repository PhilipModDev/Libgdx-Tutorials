#version 430

layout(location = 0) in vec3 a_position;
layout(location = 1) in vec2 a_texCoord0;

uniform mat4 modelView;
uniform mat4 projection;
out vec2 texels;

void main(void){
    texels = a_texCoord0;
    gl_Position = projection * modelView * vec4(a_position,1.0f);
}