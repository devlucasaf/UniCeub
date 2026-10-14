using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Windows.Input;
using MeuAppMaui.Models;
using MeuAppMaui.Services;
using Microsoft.Maui.Controls;

namespace MeuAppMaui.ViewModels;

public class MainViewModel : INotifyPropertyChanged
{
    private readonly IApiService    _apiService;
    private string                  _mensagem = "Clique no botão";
    private int                     _contador = 0;
    private Item?                   _itemAtual;

    public MainViewModel(IApiService apiService)
    {
        _apiService = apiService;
        IncrementarCommand = new Command(Incrementar);
        CarregarItemCommand = new Command(async () => await CarregarItemAsync());
    }

    public string Mensagem
    {
        get => _mensagem;
        set { _mensagem = value; OnPropertyChanged(); }
    }

    public int Contador
    {
        get => _contador;
        set { _contador = value; OnPropertyChanged(); Mensagem = $"Clicado {_contador} vez(es)"; }
    }

    public Item? ItemAtual
    {
        get => _itemAtual;
        set { _itemAtual = value; OnPropertyChanged(); }
    }

    public ICommand IncrementarCommand { get; }
    public ICommand CarregarItemCommand { get; }

    private void Incrementar()
    {
        Contador++;
    }

    private async Task CarregarItemAsync()
    {
        ItemAtual = await _apiService.ObterItemAsync(1);
    }

    public event PropertyChangedEventHandler? PropertyChanged;
    protected void OnPropertyChanged([CallerMemberName] string? name = null) =>
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(name));
}