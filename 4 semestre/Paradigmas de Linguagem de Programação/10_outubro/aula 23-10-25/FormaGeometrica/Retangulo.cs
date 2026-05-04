/*
Paradigmas de Linguagem de Programação
Aula: 23-10-25
Slide 28: Linguagem OO
*/

using System;

namespace FormaGeometrica
{
    class Retangulo
    {
        // Campos privados (backing fields) para armazenar os dados com segurança
        private float _altura;
        private float _base;

        // Propriedade com validação no SET
        public float Altura
        {
            get => _altura;
            set => _altura = value > 0 ? value : throw new ArgumentException("A altura deve ser maior que zero.");
        }

        public float Base
        {
            get => _base;
            set => _base = value > 0 ? value : throw new ArgumentException("A base deve ser maior que zero.");
        }

        public float Area => Altura * Base;

        public Retangulo(float altura, float @base)
        {
            Altura = altura;
            Base = @base;
        }

        public override string ToString()
        {
            return $"Retângulo [Base: {Base} | Altura: {Altura} | Área: {Area}]";
        }
    }
}



